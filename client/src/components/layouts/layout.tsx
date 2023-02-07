import Navbar from "@/components/navigation/navbar/navbar";

export type Props = {
  children: React.ReactNode;
};

export default function Layout({ children }: Props) {
  return (
    <div>
      <Navbar />
      <div className="p-8">{children}</div>
    </div>
  );
}
